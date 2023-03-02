package com.lyfebloc.ethereumkit.spv.core

import com.lyfebloc.ethereumkit.core.TransactionBuilder
import com.lyfebloc.ethereumkit.models.Transaction
import com.lyfebloc.ethereumkit.models.RawTransaction
import com.lyfebloc.ethereumkit.models.Signature
import com.lyfebloc.ethereumkit.spv.net.handlers.SendTransactionTaskHandler
import com.lyfebloc.ethereumkit.spv.net.tasks.SendTransactionTask

class TransactionSender(
        private val transactionBuilder: TransactionBuilder,
) : SendTransactionTaskHandler.Listener {

    interface Listener {
        fun onSendSuccess(sendId: Int, transaction: Transaction)
        fun onSendFailure(sendId: Int, error: Throwable)
    }

    var listener: Listener? = null

    fun send(sendId: Int, taskPerformer: ITaskPerformer, rawTransaction: RawTransaction, signature: Signature) {
        taskPerformer.add(SendTransactionTask(sendId, rawTransaction, signature))
    }

    override fun onSendSuccess(task: SendTransactionTask) {
        val transaction = transactionBuilder.transaction(task.rawTransaction, task.signature)

        listener?.onSendSuccess(task.sendId, transaction)
    }

    override fun onSendFailure(task: SendTransactionTask, error: Throwable) {
        listener?.onSendFailure(task.sendId, error)
    }

}

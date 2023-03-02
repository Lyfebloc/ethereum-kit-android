package com.lyfebloc.ethereumkit.spv.core

import com.lyfebloc.ethereumkit.core.ISpvStorage
import com.lyfebloc.ethereumkit.models.Address
import com.lyfebloc.ethereumkit.spv.models.AccountStateSpv
import com.lyfebloc.ethereumkit.spv.models.BlockHeader
import com.lyfebloc.ethereumkit.spv.net.handlers.AccountStateTaskHandler
import com.lyfebloc.ethereumkit.spv.net.tasks.AccountStateTask

class AccountStateSyncer(private val storage: ISpvStorage,
                         private val address: Address) : AccountStateTaskHandler.Listener {

    interface Listener {
        fun onUpdate(accountState: AccountStateSpv)
    }

    var listener: Listener? = null

    fun sync(taskPerformer: ITaskPerformer, blockHeader: BlockHeader) {
        taskPerformer.add(AccountStateTask(address, blockHeader))
    }

    override fun didReceive(accountState: AccountStateSpv, address: Address, blockHeader: BlockHeader) {
        storage.saveAccountSate(accountState)
        listener?.onUpdate(accountState)
    }

}

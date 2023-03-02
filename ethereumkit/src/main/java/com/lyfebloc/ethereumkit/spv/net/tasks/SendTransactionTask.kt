package com.lyfebloc.ethereumkit.spv.net.tasks

import com.lyfebloc.ethereumkit.spv.core.ITask
import com.lyfebloc.ethereumkit.models.RawTransaction
import com.lyfebloc.ethereumkit.models.Signature

class SendTransactionTask(val sendId: Int,
                          val rawTransaction: RawTransaction,
                          val signature: Signature) : ITask

package com.lyfebloc.ethereumkit.spv.net.tasks

import com.lyfebloc.ethereumkit.models.Address
import com.lyfebloc.ethereumkit.spv.core.ITask
import com.lyfebloc.ethereumkit.spv.models.BlockHeader

class AccountStateTask(val address: Address, val blockHeader: BlockHeader) : ITask

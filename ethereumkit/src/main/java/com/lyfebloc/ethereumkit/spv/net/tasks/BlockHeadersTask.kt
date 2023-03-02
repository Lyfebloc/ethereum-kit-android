package com.lyfebloc.ethereumkit.spv.net.tasks

import com.lyfebloc.ethereumkit.spv.core.ITask
import com.lyfebloc.ethereumkit.spv.models.BlockHeader

class BlockHeadersTask(val blockHeader: BlockHeader, val limit: Int, val reverse: Boolean = false) : ITask

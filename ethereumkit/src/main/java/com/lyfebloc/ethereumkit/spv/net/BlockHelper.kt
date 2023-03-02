package com.lyfebloc.ethereumkit.spv.net

import com.lyfebloc.ethereumkit.core.ISpvStorage
import com.lyfebloc.ethereumkit.network.INetwork
import com.lyfebloc.ethereumkit.spv.models.BlockHeader

class BlockHelper(val storage: ISpvStorage, val network: INetwork) {

    val lastBlockHeader: BlockHeader
        get() = storage.getLastBlockHeader() ?: network.checkpointBlock

}

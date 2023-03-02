package com.lyfebloc.ethereumkit.spv.net.tasks

import com.lyfebloc.ethereumkit.network.INetwork
import com.lyfebloc.ethereumkit.spv.core.ITask
import com.lyfebloc.ethereumkit.spv.models.BlockHeader
import java.math.BigInteger

class HandshakeTask(val peerId: String, network: INetwork, blockHeader: BlockHeader) : ITask {
    val networkId: Int = network.id
    val genesisHash: ByteArray = network.genesisBlockHash
    val headTotalDifficulty: BigInteger = blockHeader.totalDifficulty
    val headHash: ByteArray = blockHeader.hashHex
    val headHeight: Long = blockHeader.height
}

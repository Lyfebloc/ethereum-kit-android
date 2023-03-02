package com.lyfebloc.ethereumkit.spv.net.les.messages

import com.lyfebloc.ethereumkit.core.toHexString
import com.lyfebloc.ethereumkit.spv.core.toBigInteger
import com.lyfebloc.ethereumkit.spv.core.toLong
import com.lyfebloc.ethereumkit.spv.net.IInMessage
import com.lyfebloc.ethereumkit.spv.rlp.RLP
import com.lyfebloc.ethereumkit.spv.rlp.RLPList
import java.math.BigInteger

class AnnounceMessage(payload: ByteArray) : IInMessage {

    val blockHash: ByteArray
    val blockHeight: Long
    val blockTotalDifficulty: BigInteger
    val reorganizationDepth: Long

    init {
        val params = RLP.decode2(payload)[0] as RLPList
        blockHash = params[0].rlpData ?: byteArrayOf()
        blockHeight = params[1].rlpData.toLong()
        blockTotalDifficulty = params[2].rlpData.toBigInteger()
        reorganizationDepth = params[3].rlpData.toLong()
    }

    override fun toString(): String {
        return "Announce [blockHash: ${blockHash.toHexString()}; " +
                "blockHeight: $blockHeight; " +
                "blockTotalDifficulty: $blockTotalDifficulty; " +
                "reorganizationDepth: $reorganizationDepth]"
    }
}

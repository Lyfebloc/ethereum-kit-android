package com.lyfebloc.nftkit.decorations

import com.lyfebloc.ethereumkit.decorations.TransactionDecoration
import com.lyfebloc.ethereumkit.models.Address
import com.lyfebloc.ethereumkit.models.TransactionTag
import com.lyfebloc.nftkit.models.TokenInfo
import java.math.BigInteger

class OutgoingEip1155Decoration(
    val contractAddress: Address,
    val to: Address,
    val tokenId: BigInteger,
    val value: BigInteger,
    val sentToSelf: Boolean,
    val tokenInfo: TokenInfo?,
) : TransactionDecoration() {

    override fun tags(): List<String> =
        listOf(contractAddress.hex, EIP1155_TRANSFER, TransactionTag.tokenOutgoing(contractAddress.hex), TransactionTag.OUTGOING)

    companion object {
        const val EIP1155_TRANSFER = "eip1155Transfer"
    }
}

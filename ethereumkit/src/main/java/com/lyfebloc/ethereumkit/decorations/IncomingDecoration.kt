package com.lyfebloc.ethereumkit.decorations

import com.lyfebloc.ethereumkit.models.Address
import com.lyfebloc.ethereumkit.models.TransactionTag
import java.math.BigInteger

class IncomingDecoration(
    val from: Address,
    val value: BigInteger
) : TransactionDecoration() {

    override fun tags(): List<String> =
        listOf(TransactionTag.EVM_COIN, TransactionTag.EVM_COIN_INCOMING, TransactionTag.INCOMING)

}

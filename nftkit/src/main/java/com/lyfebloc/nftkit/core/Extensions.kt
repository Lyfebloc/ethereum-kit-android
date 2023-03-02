package com.lyfebloc.nftkit.core

import com.lyfebloc.ethereumkit.contracts.ContractEventInstance
import com.lyfebloc.ethereumkit.core.hexStringToByteArrayOrNull
import com.lyfebloc.ethereumkit.models.Address
import com.lyfebloc.ethereumkit.models.TransactionLog
import com.lyfebloc.nftkit.events.Eip721TransferEventInstance
import java.math.BigInteger

fun TransactionLog.getEip721EventInstance(): ContractEventInstance? {
    val signature = topics.firstOrNull()?.hexStringToByteArrayOrNull() ?: return null

    if (!signature.contentEquals(Eip721TransferEventInstance.signature) || data.size != 96) {
        return null
    }

    val from = data.copyOfRange(0, 32)
    val to = data.copyOfRange(32, 64)
    val tokenId = data.copyOfRange(64, 96)

    return Eip721TransferEventInstance(
        address,
        Address(from),
        Address(to),
        BigInteger(tokenId)
    )
}

fun TransactionLog.getEip1155EventInstance(): ContractEventInstance? {
    val signature = topics.firstOrNull()?.hexStringToByteArrayOrNull() ?: return null

    // todo

    return null
}

package com.lyfebloc.nftkit.contracts

import com.lyfebloc.ethereumkit.contracts.ContractMethod
import com.lyfebloc.ethereumkit.models.Address
import java.math.BigInteger

class Eip721SafeTransferFromMethod(
    val from: Address,
    val to: Address,
    val tokenId: BigInteger,
    val data: ByteArray
) : ContractMethod() {

    override val methodSignature = Companion.methodSignature
    override fun getArguments() = listOf(from, to, tokenId, data)

    companion object {
        const val methodSignature = "safeTransferFrom(address,address,uint256,bytes)"
    }

}

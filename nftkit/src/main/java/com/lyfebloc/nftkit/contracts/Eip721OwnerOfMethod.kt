package com.lyfebloc.nftkit.contracts

import com.lyfebloc.ethereumkit.contracts.ContractMethod
import java.math.BigInteger

class Eip721OwnerOfMethod(
    private val tokenId: BigInteger
) : ContractMethod() {

    override val methodSignature = Companion.methodSignature

    override fun getArguments() = listOf(tokenId)

    companion object {
        const val methodSignature = "ownerOf(uint256)"
    }
}

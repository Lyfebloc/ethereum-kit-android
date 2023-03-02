package com.lyfebloc.nftkit.contracts

import com.lyfebloc.ethereumkit.contracts.ContractMethod
import com.lyfebloc.ethereumkit.models.Address
import java.math.BigInteger

class Eip1155BalanceOfMethod(
    private val owner: Address,
    private val tokenId: BigInteger
) : ContractMethod() {

    override val methodSignature = Companion.methodSignature

    override fun getArguments() = listOf(owner, tokenId)

    companion object {
        const val methodSignature = "balanceOf(address,uint256)"
    }
}

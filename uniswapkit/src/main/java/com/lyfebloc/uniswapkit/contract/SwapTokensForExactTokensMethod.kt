package com.lyfebloc.uniswapkit.contract

import com.lyfebloc.ethereumkit.contracts.ContractMethod
import com.lyfebloc.ethereumkit.models.Address
import java.math.BigInteger

class SwapTokensForExactTokensMethod(
        val amountOut: BigInteger,
        val amountInMax: BigInteger,
        val path: List<Address>,
        val to: Address,
        val deadline: BigInteger
) : ContractMethod() {

    override val methodSignature = Companion.methodSignature
    override fun getArguments() = listOf(amountOut, amountInMax, path, to, deadline)

    companion object {
        const val methodSignature = "swapTokensForExactTokens(uint256,uint256,address[],address,uint256)"
    }

}

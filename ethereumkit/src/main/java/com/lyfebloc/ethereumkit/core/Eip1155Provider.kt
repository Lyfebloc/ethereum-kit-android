package com.lyfebloc.ethereumkit.core

import com.lyfebloc.ethereumkit.api.core.IRpcApiProvider
import com.lyfebloc.ethereumkit.api.core.NodeApiProvider
import com.lyfebloc.ethereumkit.api.core.RpcBlockchain
import com.lyfebloc.ethereumkit.contracts.ContractMethod
import com.lyfebloc.ethereumkit.models.Address
import com.lyfebloc.ethereumkit.models.DefaultBlockParameter
import com.lyfebloc.ethereumkit.models.RpcSource
import com.lyfebloc.ethereumkit.spv.core.toBigInteger
import io.reactivex.Single
import java.math.BigInteger

class Eip1155Provider(
    private val provider: IRpcApiProvider
) {

    class BalanceOfMethod(val owner: Address, val tokenId: BigInteger) : ContractMethod() {
        override val methodSignature = "balanceOf(address,uint256)"
        override fun getArguments() = listOf(owner, tokenId)
    }

    fun getTokenBalance(contractAddress: Address, tokenId: BigInteger, address: Address): Single<BigInteger> {
        val callRpc = RpcBlockchain.callRpc(contractAddress, BalanceOfMethod(address, tokenId).encodedABI(), DefaultBlockParameter.Latest)

        return provider
            .single(callRpc)
            .map { it.sliceArray(IntRange(0, 31)).toBigInteger() }
    }

    companion object {

        fun instance(rpcSource: RpcSource.Http): Eip1155Provider {
            val apiProvider = NodeApiProvider(rpcSource.urls, EthereumKit.gson, rpcSource.auth)

            return Eip1155Provider(apiProvider)
        }

    }

}

package com.lyfebloc.erc20kit.core

import com.lyfebloc.erc20kit.contract.BalanceOfMethod
import com.lyfebloc.ethereumkit.core.EthereumKit
import com.lyfebloc.ethereumkit.models.Address
import com.lyfebloc.ethereumkit.spv.core.toBigInteger
import io.reactivex.Single
import java.math.BigInteger

class DataProvider(
        private val ethereumKit: EthereumKit
) : IDataProvider {

    override fun getBalance(contractAddress: Address, address: Address): Single<BigInteger> {
        return ethereumKit.call(contractAddress, BalanceOfMethod(address).encodedABI())
                .map { it.sliceArray(IntRange(0, 31)).toBigInteger() }
    }

}

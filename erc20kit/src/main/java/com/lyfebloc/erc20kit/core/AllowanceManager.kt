package com.lyfebloc.erc20kit.core

import com.lyfebloc.erc20kit.contract.AllowanceMethod
import com.lyfebloc.erc20kit.contract.ApproveMethod
import com.lyfebloc.ethereumkit.core.EthereumKit
import com.lyfebloc.ethereumkit.core.toRawHexString
import com.lyfebloc.ethereumkit.models.Address
import com.lyfebloc.ethereumkit.models.DefaultBlockParameter
import com.lyfebloc.ethereumkit.models.TransactionData
import io.reactivex.Single
import java.math.BigInteger

class AllowanceManager(
        private val ethereumKit: EthereumKit,
        private val contractAddress: Address,
        private val address: Address
) {

    fun allowance(spenderAddress: Address, defaultBlockParameter: DefaultBlockParameter): Single<BigInteger> {
        return ethereumKit
                .call(contractAddress, AllowanceMethod(address, spenderAddress).encodedABI(), defaultBlockParameter)
                .map { result ->
                    BigInteger(result.sliceArray(0..31).toRawHexString(), 16)
                }
    }

    fun approveTransactionData(spenderAddress: Address, amount: BigInteger): TransactionData {
        return TransactionData(contractAddress, BigInteger.ZERO, ApproveMethod(spenderAddress, amount).encodedABI())
    }

}

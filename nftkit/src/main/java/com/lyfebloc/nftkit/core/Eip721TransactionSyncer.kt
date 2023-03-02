package com.lyfebloc.nftkit.core

import com.lyfebloc.ethereumkit.core.ITransactionProvider
import com.lyfebloc.ethereumkit.core.ITransactionSyncer
import com.lyfebloc.ethereumkit.models.ProviderEip721Transaction
import com.lyfebloc.ethereumkit.models.Transaction
import com.lyfebloc.nftkit.models.Eip721Event
import com.lyfebloc.nftkit.models.Nft
import com.lyfebloc.nftkit.models.NftType
import io.reactivex.Single

class Eip721TransactionSyncer(
    private val transactionProvider: ITransactionProvider,
    private val storage: Storage
) : ITransactionSyncer {

    var listener: ITransactionSyncerListener? = null

    private fun handle(transactions: List<ProviderEip721Transaction>) {
        if (transactions.isEmpty()) return

        val events = transactions.map { tx ->
            Eip721Event(tx.hash, tx.blockNumber, tx.contractAddress, tx.from, tx.to, tx.tokenId, tx.tokenName, tx.tokenSymbol, tx.tokenDecimal)
        }

        storage.saveEip721Events(events)

        val nfts = events.map { event ->
            Nft(
                NftType.Eip721,
                contractAddress = event.contractAddress,
                tokenId = event.tokenId,
                tokenName = event.tokenName
            )
        }.distinct()

        listener?.didSync(nfts, NftType.Eip721)
    }

    override fun getTransactionsSingle(): Single<Pair<List<Transaction>, Boolean>> {
        val lastTransactionBlockNumber = storage.lastEip721Event()?.blockNumber ?: 0
        val initial: Boolean = lastTransactionBlockNumber == 0L

        return transactionProvider.getEip721Transactions(lastTransactionBlockNumber + 1)
            .doOnSuccess { providerTokenTransactions -> handle(providerTokenTransactions) }
            .map { providerTokenTransactions ->
                val array = providerTokenTransactions.map { transaction ->
                    Transaction(
                        hash = transaction.hash,
                        timestamp = transaction.timestamp,
                        isFailed = false,
                        blockNumber = transaction.blockNumber,
                        transactionIndex = transaction.transactionIndex,
                        nonce = transaction.nonce,
                        gasPrice = transaction.gasPrice,
                        gasLimit = transaction.gasLimit,
                        gasUsed = transaction.gasUsed
                    )

                }
                Pair(array, initial)
            }
            .onErrorReturnItem(Pair(listOf(), initial))
    }
}

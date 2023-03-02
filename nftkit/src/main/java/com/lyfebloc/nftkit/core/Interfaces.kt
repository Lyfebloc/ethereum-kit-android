package com.lyfebloc.nftkit.core

import com.lyfebloc.nftkit.models.Nft
import com.lyfebloc.nftkit.models.NftType

interface ITransactionSyncerListener {
    fun didSync(nfts: List<Nft>, type: NftType)
}

interface IBalanceSyncManagerListener {
    fun didFinishSyncBalances()
}

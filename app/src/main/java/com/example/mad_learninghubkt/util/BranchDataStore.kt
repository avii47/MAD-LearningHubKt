package com.example.mad_learninghubkt.util

import com.example.mad_learninghubkt.data.BranchesItem

object BranchDataStore {

    private var branchList: MutableList<BranchesItem> = mutableListOf()

    fun setBranchData(branch: BranchesItem) {
        branchList.add(branch)
    }

    fun getBranchData(): List<BranchesItem> {
        return branchList.toList()
    }
}
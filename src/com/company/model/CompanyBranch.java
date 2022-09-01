package com.company.model;

public abstract class CompanyBranch {

    String branchId;
    String branchName;

    public CompanyBranch(String branchName) {
        this.branchName = branchName;
        setBranchId();
    }

    public void setBranchId() {
        this.branchId = branchName;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }
}

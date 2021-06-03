package com.epam.rd.autocode.observer.git;

public class GitRepoObservers {
    public static Repository newRepository(){
        return new StandardRepository();
    }

    public static WebHook mergeToBranchWebHook(String branchName){
        return new StandardWebHook(Event.Type.MERGE, branchName);
    }

    public static WebHook commitToBranchWebHook(String branchName){
        return new StandardWebHook(Event.Type.COMMIT, branchName);
    }


}
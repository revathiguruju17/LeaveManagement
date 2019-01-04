class Approver {
    String approveLeave(int numberOfLeavesLeft, int numberOfLeavesWant) {
        if (numberOfLeavesLeft == 0) {
            return "No Annual leaves left";
        } else if (numberOfLeavesLeft >= numberOfLeavesWant)
            return "leave approved";
        else {
            return "only " + numberOfLeavesLeft + " leaves are approved";
        }
    }
}

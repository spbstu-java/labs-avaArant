class crawl implements Move {
    private static final int DISTANCE = 1;

    @Override
    public int move(int start) { return start + DISTANCE;}
}

class walk implements Move {
    private static final int DISTANCE = 10;

    @Override
    public int move(int start) {return start + DISTANCE;}
}

class run implements Move {
    private static final int DISTANCE = 25;

    @Override
    public int move(int start) { return start + DISTANCE;}
}

class drive implements Move {
    private static final int DISTANCE = 50;

    @Override
    public int move(int start) { return start + DISTANCE;}
}
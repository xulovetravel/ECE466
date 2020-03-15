import TokenBucket.TokenBucket;

public class Bucketmain {
    public static void main(String[] args) {
    // listen on port 4444, send to localhost:4445,
    // max. size of received packet is 1024 bytes,
    // buffer capacity is 100*1024 bytes,
    // token bucket has 10000 tokens, rate 5000 tokens/sec, and
    // records packet arrivals to bucket.txt).
    //TokenBucket lb = new TokenBucket(4445, "localhost", 4446, 65507, 100*65507, 100, 250000, "ex1_bucket.txt");
    //TokenBucket lb = new TokenBucket(4445, "localhost", 4446, 65507, 100*65507, 500, 125000, "ex2_bucket.txt");
    //TokenBucket lb = new TokenBucket(4445, "localhost", 4446, 65507, 100*65507, 100, 100000, "ex3_bucket.txt");

    //Section 2.4: token bucket has 10000 tokens. 
    //Experiment (1): 10000 tokens, maximum size of token is 10000 
    TokenBucket lb = new TokenBucket(4445, "localhost", 4446, 1001, 10000*10000, 10000, 8000000, "max_bucket_1.txt");
    //Experiment (2): 10000 tokens, max size of token is 10000 (given), with buffer capacity of 
    //TokenBucket lb = new TokenBucket(4445, "localhost", 4446, 1001, 10000*10000, 10000, 8000000, "max_bucket_2.txt");
    new Thread(lb).start();
    }
}


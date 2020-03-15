import TokenBucket.TokenBucket;

public class Bucketmain {
    public static void main(String[] args) {
    // listen on port 4444, send to localhost:4445,
    // max. size of received packet is 1024 bytes,
    // buffer capacity is 100*1024 bytes,
    // token bucket has 10000 tokens, rate 5000 tokens/sec, and
    // records packet arrivals to bucket.txt).

    //excercise 3.1 poisson, 300000 tokens/sec. 
    //TokenBucket lb = new TokenBucket (4445, "localhost", 4446, 65507, 6550700, 1500, 300000, "bucket_poisson31.txt");
        
    //exercise 3.1 ethernet
    //TokenBucket lb = new TokenBucket (4445, "localhost", 4446, 65507, 65507*10000, 1518,  1000000, "bucket_ethernet31.txt");

    //exercise 3.1 video
    TokenBucket lb = new TokenBucket (4445, "localhost", 4446, 65507, 65507000, 660000,  4400000, "bucket_video31.txt");
        
        
 
        
    //excercise 3.2 poisson
    //TokenBucket lb = new TokenBucket (4445, "localhost", 4446, 65507, 6550700, 5000, 125075, "bucket_poisson32.txt");

    //exercise 3.2 ethernet
    //TokenBucket lb = new TokenBucket (4445, "localhost", 4446, 65507, 655070000, 170000,  137500, "bucket_ethernet32.txt");     

    //exercise 3.2 video
    //burst   
    //TokenBucket lb = new TokenBucket (4445, "localhost", 4446, 65507, 655070000, 56700000,  1800000, "bucket_video32.txt");
        
        

        
    new Thread(lb).start();
    }
}


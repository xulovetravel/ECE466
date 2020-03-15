clc;clear all;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Reading data from a file
%Note that time is in micro seconds and packetsize is in Bytes
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%output from the input trace. 
[packet_no_p, time_p, packetsize_p] = textread('poisson3.data', '%f %f %f');
time1 = zeros(1,80000);
arrival1 = zeros(1,80000);

time1(1) = time_p(1);
arrival1(1) = packetsize_p(1);
i=2;
while i<=80000
    time1(i) = time_p(i);
    arrival1(i) = arrival1(i-1) + packetsize_p(i);
    i=i+1;
end
    
%output from the token bucket
[arrival_time, packetsize_p2, back_log, num_of_tokens] = textread('bucket_poisson32.txt', '%f %f %f %f');
time2 = zeros(1,80000);
arrival2 = zeros(1,80000);

time2(1) = arrival_time(1);
arrival2(1) = packetsize_p2(1);
i=2;
while i<=80000
    time2(i) = time2(i-1) + arrival_time(i);
    arrival2(i) = arrival2(i-1) + packetsize_p2(i);
    i=i+1;
end

%Output for the sink.
[packet_no_p3, packetsize_p3, arrival_time] = textread('TrafficSinkOutputPoisson32.txt', '%f %f %f');
time3 = zeros(1,80000);
arrival3 = zeros(1,80000);

time3(1) = arrival_time(1);
arrival3(1) = packetsize_p3(1);
i=2;
while i<=80000
    time3(i) = time3(i-1) + arrival_time(i);
    arrival3(i) = arrival3(i-1) + packetsize_p3(i);
    i=i+1;
end

%First plot shows the plot for backlog, as well as the token. 
figure(1);
plotToken = zeros(1,80000);
plotBacklog = zeros(1,80000);
i=1;
while i<=80000
    plotToken(i) = num_of_tokens(i);
    plotBacklog(i) = back_log(i);
    i=i+1;
end
h1 = plot(time2, plotToken, 'r',time2, plotBacklog,'b')
hold on
legned_1= legend(h1,'# of tokens','backlog result');

title('Token Bucket for Poisson');
xlabel('time (in microseconds)');
ylabel('token bucket and backlog (in bytes)');
%axis([0 7000000 0 50000])
hold off
%Second plot is the result for input file, bucket and output file. 
figure(2);
h2 = plot(time1,arrival1, 'r', time2,arrival2, 'g', time3,arrival3, 'b');
hold on
legend_2 = legend(h2, 'input trace file', 'bucket arrivals', 'output file');
title('Result for Poisson3.txt');
xlabel('time (in microseconds)');
ylabel('packets arrival (in bytes)');
hold off

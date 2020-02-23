clc;clear all;
%% Section 1.1
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Reading data from a file
%Note that time is in micro seconds and packetsize is in Bytes
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
[packet_no_p, time_p, packetsize_p] = textread('poisson1.data', '%f %f %f');

%%%%%%%%%%%%%%%%%%%%%%%%%Exercise 1.2%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%The following code will generate Plot 1; You generate Plot2 , Plot3.
%Hint1: For Plot2 and Plot3, you only need to change 'initial_p', the
%       initial time in microseconds, and 'ag_frame', the time period of
%       aggregation
%Hint2: After adding Plot2 and Plot3 to this part, you can use 'subplot(3,1,2);'
%       and 'subplot(3,1,3);' respectively to show 3 plots in the same figure.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

time_difference=zeros(1,125000);
i=1;
while i <125000
    time_difference(i)=time_p(i+1)-time_p(i);
    i=i+1;
end
M=mean(time_difference);
V=var(time_difference);

%%%% three plots
figure(1);
jj=1;
i=1;
initial_p=0;
ag_time=1000000;
bytes_p=zeros(1,100);
while time_p(jj)<=initial_p
    jj=jj+1;
end
while i<=100
while ((time_p(jj)-initial_p)<=ag_time*i && jj<length(packetsize_p))
bytes_p(i)=bytes_p(i)+packetsize_p(jj);
jj=jj+1;
end
i=i+1;
end

%%%%%%%%
subplot(3,1,1);bar(bytes_p);title("Plot 1 starting from 0 with 1 second difference");xlabel("Time Period/second");ylabel("Traffic Size/bytes");

%%%%%%%%
initial_p2=30000000;
ag_time2=100000;
jj=1;
i=1;
bytes_p2=zeros(1,100);
x_2=zeros(1,100);
while time_p(jj)<=initial_p2
    jj=jj+1;
end
while i<=100
while ((time_p(jj)-initial_p2)<=ag_time2*i && jj<length(packetsize_p))
bytes_p2(i)=bytes_p2(i)+packetsize_p(jj);
x_2(i)=30+0.1*i;
jj=jj+1;
end
i=i+1;
end
subplot(3,1,2);
bar(x_2,bytes_p2);title("Plot 2 starting from 30 with 0.1 second difference");xlabel("Time Period/second");ylabel("Traffic Size/bytes");

%%%%
initial_p3=5000000;
ag_time3=10000;
jj=1;
i=1;
bytes_p3=zeros(1,100);
x_3=zeros(1,100);
while time_p(jj)<=initial_p3
    jj=jj+1;
end
while i<=100
while ((time_p(jj)-initial_p3)<=ag_time3*i && jj<length(packetsize_p))
bytes_p3(i)=bytes_p3(i)+packetsize_p(jj);
x_3(i)=50+0.01*i;
jj=jj+1;
end
i=i+1;
end
subplot(3,1,3);
bar(x_3,bytes_p3);title("Plot 3 starting from 50 with 0.01 second difference");xlabel("Time Period/second");ylabel("Traffic Size/bytes");
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Note: Run the same MATLAB code for Exercise 1.3 and 1.4 but change the
%second line of the code in order to read the files 'poisson2.data' and
%'poisson3.data' respectively.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%% Section 1.3

[packet_no_p, time_p, packetsize_p] = textread('poisson3.data', '%f %f %f');

%%%%%%%%%%%%%%%%%%%%%%%%%Exercise 1.2%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%The following code will generate Plot 1; You generate Plot2 , Plot3.
%Hint1: For Plot2 and Plot3, you only need to change 'initial_p', the
%       initial time in microseconds, and 'ag_frame', the time period of
%       aggregation
%Hint2: After adding Plot2 and Plot3 to this part, you can use 'subplot(3,1,2);'
%       and 'subplot(3,1,3);' respectively to show 3 plots in the same figure.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

time_difference=zeros(1,125000);
i=1;
while i <125000
    time_difference(i)=time_p(i+1)-time_p(i);
    i=i+1;
end
M=mean(time_difference);
Mb=mean(packetsize_p);
V=var(time_difference);

%%%% three plots
figure(1);
jj=1;
i=1;
initial_p=0;
ag_time=1000000;
bytes_p=zeros(1,100);
while time_p(jj)<=initial_p
    jj=jj+1;
end
while i<=100
while ((time_p(jj)-initial_p)<=ag_time*i && jj<length(packetsize_p))
bytes_p(i)=bytes_p(i)+packetsize_p(jj);
jj=jj+1;
end
i=i+1;
end

%%%%%%%%
subplot(3,1,1);bar(bytes_p);title("Plot 1 starting from 0 with 1 second difference");xlabel("Time Period/second");ylabel("Traffic Size/bytes");

%%%%%%%%
initial_p2=30000000;
ag_time2=100000;
jj=1;
i=1;
bytes_p2=zeros(1,100);
x_2=zeros(1,100);
while time_p(jj)<=initial_p2
    jj=jj+1;
end
while i<=100
while ((time_p(jj)-initial_p2)<=ag_time2*i && jj<length(packetsize_p))
bytes_p2(i)=bytes_p2(i)+packetsize_p(jj);
x_2(i)=30+0.1*i;
jj=jj+1;
end
i=i+1;
end
subplot(3,1,2);
bar(x_2,bytes_p2);title("Plot 2 starting from 30 with 0.1 second difference");xlabel("Time Period/second");ylabel("Traffic Size/bytes");

%%%%
initial_p3=5000000;
ag_time3=10000;
jj=1;
i=1;
bytes_p3=zeros(1,100);
x_3=zeros(1,100);
while time_p(jj)<=initial_p3
    jj=jj+1;
end
while i<=100
while ((time_p(jj)-initial_p3)<=ag_time3*i && jj<length(packetsize_p))
bytes_p3(i)=bytes_p3(i)+packetsize_p(jj);
x_3(i)=50+0.01*i;
jj=jj+1;
end
i=i+1;
end
subplot(3,1,3);
bar(x_3,bytes_p3);title("Plot 3 starting from 50 with 0.01 second difference");xlabel("Time Period/second");ylabel("Traffic Size/bytes");




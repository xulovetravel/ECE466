clc;clear all;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Reading the data and putting the first 100000 entries in variables 
%Note that time is in seconds and framesize is in Bytes
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
no_entries=100000;
[time1, framesize1] = textread('Bel.data', '%f %f');
time=time1(1:no_entries);
framesize=framesize1(1:no_entries);
%%%%%%%%%%%%%%%%%%%%%%%%%Exercise %%%3.2%%%%%%%%%%%%%%%%%%%%%%%%%%%
%The following code will generate Plot 1; You generate Plot2 , Plot3.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

fprintf("Number of packets is: %d \n\n",length(framesize1));
fprintf("Number of bytes is: %d \n\n",sum(framesize1)); 
mean_bit_rate = sum(framesize1) * 8/max(time1);
fprintf("Average bit rate is: %f \n\n",mean_bit_rate); 

rate = zeros(1,length(framesize1));
rate(1) = framesize1(1)*8/time1(1);
i = 2;
while i <= length(framesize1)
    rate(i) = framesize1(i)*8/(time1(i) - time1(i-1));
    i = i + 1;
end

fprintf("Peak bit rate is: %f \n\n",max(rate)); 
figure(1);
plot(time1,framesize1);
title('packet size as a function of time');
xlabel('time(s)');
ylabel('packet(bytes)');

figure(4);
[n,x] = hist(framesize1,80);
bar(x,n./sum(n));
title('distribution of packet sizes');
xlabel('packet size');
ylabel('relative Frequency');

%%%%%%%%%%%%%%%%%%%%%%%%%Exercise %3.3%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
figure(3);
jj=1;
i=1;
initial_p=0;
ag_time=1;
bytes_p=zeros(1,100);
while time(jj)<=initial_p
    jj=jj+1;
end
while i<=100
while ((time(jj)-initial_p)<=ag_time*i && jj<no_entries)
bytes_p(i)=bytes_p(i)+framesize(jj);
jj=jj+1;
end
i=i+1;
end
%%%%%%%%
subplot(3,1,1);bar(bytes_p);
title('Plot 1, start at 1');
xlabel('1s for every byte');
ylabel('# of bytes');
%%Second: set random point starting at 20.
jj=1;
i=1;
initial_p=20;
ag_time=0.1;
bytes_p2=zeros(1,100);
while time(jj)<=initial_p
    jj=jj+1;
end
while i<=100
while ((time(jj)-initial_p)<=ag_time*i && jj<no_entries)
bytes_p2(i)=bytes_p2(i)+framesize(jj);
jj=jj+1;
end
i=i+1;
end
%%%%%%%%
subplot(3,1,2);bar([20:0.1:29.9], bytes_p2);
title('Plot 2, start at 20');
xlabel('0.1s for every byte');
ylabel('# of bytes');

%%Third: set random point starting at 90.
jj=1;
i=1;
initial_p=90;
ag_time=0.01;
bytes_p3=zeros(1,100);
while time(jj)<=initial_p
    jj=jj+1;
end
while i<=100
while ((time(jj)-initial_p)<=ag_time*i && jj<no_entries)
bytes_p3(i)=bytes_p3(i)+framesize(jj);
jj=jj+1;
end
i=i+1;
end
%%%%%%%%Graph for third one%%%%%%%%%%%
subplot(3,1,3);bar([90:0.01:90.99], bytes_p3);
title('Plot 3, start at 90');
xlabel('0.01s for every byte');
ylabel('# of bytes');

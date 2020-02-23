clc;clear all;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Reading data from the file
%Note: - time is in miliseconds and framesize is in Bytes
%      - file is sorted in transmit sequence
%  Column 1:   index of frame (in display sequence)
%  Column 2:   time of frame in ms (in display sequence)
%  Column 3:   type of frame (I, P, B)
%  Column 4:   size of frame (in Bytes)
%  Column 5-7: not used
%
% Since we are interested in the transmit sequence we ignore Columns 1 and
% 2. So, we are only interested in the following columns: 
%       Column 3:  assigned to type_f
%       Column 4:   assigned to framesize_f
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
[index, time, type_f, framesize_f, dummy1, dymmy2, dymmy3 ] = textread('movietrace.data', '%f %f %c %f %f %f %f');

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
%   CODE FOR EXERCISE 2.2 
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Extracting the I,P,B frmes characteristics from the source file
%frame size of I frames  : framesize_I
%frame size of P frames  : framesize_p 
%frame size of B frames  : framesize_B
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

a=0;
b=0;
c=0;
for i=1:length(index)
    if type_f(i)=='I'
        a=a+1;
        framesize_I(a)=framesize_f(i);
    end
     if type_f(i)=='B'
         b=b+1;
         framesize_B(b)=framesize_f(i);
         end
     if type_f(i)=='P'
         c=c+1;
         framesize_P(c)=framesize_f(i);
     end

end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Hint1: You may use the MATLAB functions 'length()','mean()','max()','min()'.
%       which calculate the length,mean,max,min of a
%       vector (for example max(framesize_P) will give you the size of
%       largest P frame
%Hint2: Use the 'plot' function to graph the framesize as a function of the frame
%       sequence number. 
%Hint3: Use the function 'hist' to show the distribution of the frames. Before 
%       that function type 'figure(2);' to indicate your figure number.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%number of frames
fprintf('number of frames = %d \n',length(index));

%Total number of bytes
fprintf('total number of bytes = %d \n',sum(framesize_f)); 

%size of the smallest frame, largest frame and average size of frame. 
fprintf('smallest frame size = %d bytes \n',min(framesize_f)); 
fprintf('largest frame size = %d bytes \n',max(framesize_f));
fprintf('mean frame size = %d bytes \n',mean(framesize_f));

%minimum, maximum and mean size of I frame
fprintf('smallest I frame = %d bytes \n',min(framesize_I));
fprintf('largest I frame = %d bytes \n',max(framesize_I)); 
fprintf('mean I frame = %d bytes \n',mean(framesize_I)); 

%minimum, maximum and mean size of P frame
fprintf('smallest P frame = %d bytes \n', min(framesize_P)); 
fprintf('largest P frame = %d bytes \n',max(framesize_P)); 
fprintf('mean P frame = %d bytes \n',mean(framesize_P)); 

%minimum, maximum and mean size of B frame
fprintf('smallest B frame = %d bytes \n',min(framesize_B)); 
fprintf('largest B frame = %d bytes \n',max(framesize_B)); 
fprintf('mean B frame = %d bytes \n',mean(framesize_B)); 

%mean rate and peak rate, with duration is 30. 
frame_duration = 30
meanrate =  mean(framesize_f) / (1 / frame_duration);
fprintf('mean bit rate = %d  \n',meanrate); 

peakrate =  max(framesize_f) / (1 / frame_duration);
fprintf('peak bit rate = %d  \n',peakrate); 
figure(1);
plot(index,framesize_f);
title('the frame size as a time');
xlabel('frame sequence number');
ylabel('frame size');

figure (2);
%Diagram for I frame
subplot(3,1,1);
[n,x] = hist(framesize_I,90);
bar(x,n./sum(n));
title('Distribution of I frames');
xlabel('frame size');
ylabel('relative frequency');

%Diagram for P frame
subplot(3,1,2);
[n,x] = hist(framesize_P,90);
bar(x,n./sum(n));
title('Distribution of P frames');
xlabel('frame size');
ylabel('relative frequency');

%Diagram for B frame
subplot(3,1,3);
[n,x] = hist(framesize_B,90);
bar(x,n./sum(n));
title('Distribution of B frames');
xlabel('frame size');
ylabel('relative frequency');
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
%   CODE FOR EXERCISE 2.3   (version: Spring 2007)
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%The following code will generates Plot 1. You generate Plot2 , Plot3 on
%your own. 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% The next line assigns a label (figure number) to the figure 
%figure(3);
%1: Generate a vector with 100 elements. 500 frames.
initial_point=1;
ag_frame=500;
jj=initial_point;
i=1;
bytes_f=zeros(1,100);
while i<=100
while ((jj-initial_point)<=ag_frame*i && jj<length(framesize_f))
bytes_f(i)=bytes_f(i)+framesize_f(jj);
jj=jj+1;
end
i=i+1;
end
figure(3);
subplot(3,1,1);
bar(bytes_f);
title('100 elements, 500 frames, start from 1');
xlabel('elements of 500 frames each');
ylabel('# of bytes');


%2: Generate a vector with 100 elements. 50 frames.
initial_point=3000;
ag_frame=50;
jj=initial_point;
i=1;
bytes_f_2=zeros(1,100);
while i<=100
while ((jj-initial_point)<=ag_frame*i && jj<length(framesize_f))
bytes_f_2(i)=bytes_f_2(i)+framesize_f(jj);
jj=jj+1;
end
i=i+1;
end
subplot(3,1,2);
bar([3000:1:3099], bytes_f_2);
title('100 elements, 50 frames, start from random #: 3000');
xlabel('elements of 50 frames each');
ylabel('# of bytes');




%3: Generate a vector with 100 elements. 5 frames.
initial_point=5010;
ag_frame=5;
jj=initial_point;
i=1;
bytes_f_3=zeros(1,100);
while i<=100
while ((jj-initial_point)<=ag_frame*i && jj<length(framesize_f))
bytes_f_3(i)=bytes_f_3(i)+framesize_f(jj);
jj=jj+1;
end
i=i+1;
end
subplot(3,1,3);
bar([5010:1:5109], bytes_f_3);
title('100 elements, 5 frames, start from random #: 5010 ');
xlabel('elements of 5 frames each');
ylabel('# of bytes');



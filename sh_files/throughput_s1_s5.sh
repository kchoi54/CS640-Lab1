h1 java Iperfer -s -p 5000 > output/Q3/s1_s5/throughput_h1.txt &
h5 java Iperfer -s -p 5001 > output/Q3/s1_s5/throughput_h5.txt &
h4 java Iperfer -c -h 10.0.0.1 -p 5000 -t 30 > output/Q3/s1_s5/throughput_h4.txt &
h6 java Iperfer -c -h 10.0.0.5 -p 5001 -t 30 > output/Q3/s1_s5/throughput_h6.txt

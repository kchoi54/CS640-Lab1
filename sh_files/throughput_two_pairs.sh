h1 java Iperfer -s -p 5000 > output/Q3/two_pairs/throughput_h1.txt &
h7 java Iperfer -s -p 5001 > output/Q3/two_pairs/throughput_h7.txt &
h4 java Iperfer -c -h 10.0.0.1 -p 5000 -t 30 > output/Q3/two_pairs/throughput_h4.txt &
h9 java Iperfer -c -h 10.0.0.7 -p 5001 -t 30 > output/Q3/two_pairs/throughput_h9.txt

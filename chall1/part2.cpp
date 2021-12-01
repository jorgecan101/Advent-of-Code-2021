#include <iostream>
#include <fstream>
#include <string>
#include <vector>


int main(int argc, char** argv) {

    // Idea here is to use sliding window technique to get largest sums between values

    // First we want to read the input: I will use a text file and retrieve from it
    std::ifstream myFile;
    myFile.open("input.txt");
    std::string line;
    int myCount = 0;
    int arr[2000];
    std::vector<int> resArr;

    // My idea here is to get the file into an array, and then do the sliding window algorithm inside the array

    if (myFile.is_open()) {
        while (getline(myFile, line)) {
            arr[myCount++] = stoi(line);
        }
        myFile.close();
    }

    for (int i = 0; i < sizeof(arr)/sizeof(arr[0]) - 2; i++) {
        resArr.push_back(arr[i] + arr[i+1] + arr[i+2]);

    }

    // Now we loop through the vector and increment count depending on rules from part 1
    myCount = 0;
    int prev = -1;
    for (int i = 1; i < resArr.size(); i++) {
        if (resArr[i] > prev) {
            myCount++;
        }
        prev = resArr[i];
    }
    std::cout << myCount << std::endl;
    return 0;
}

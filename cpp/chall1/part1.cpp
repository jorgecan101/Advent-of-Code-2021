#include <iostream>
#include <fstream>
#include <string>

int main(int argc, char** argv) {

    // Idea here is to count the number of times a depth measurement increases from previous measurement

    // First we want to read the input: I will use a text file and retrieve from it
    std::ifstream myFile;
    myFile.open("input.txt");
    std::string line;
    int myCount = 0;
    int prev = -1;
    if (myFile.is_open()) {
        while (getline(myFile, line)) {
            int curr = stoi(line);
            // First element has no previous so just skip forward
            if (prev == -1) {

            }
            // Check if the current element is larger than the previous element
            else if (curr > prev) {
                myCount++;

            }
            prev = curr;
        }
        myFile.close();
    }
    std::cout << myCount << std::endl;
    return 0;
}

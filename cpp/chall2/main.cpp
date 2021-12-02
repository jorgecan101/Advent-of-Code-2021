#include <iostream>
#include <fstream>
#include <string>

int part1();
int part2();

int main(int argc, char** argv) {

    std::cout << part1() << "\n";
    std::cout << part2() << "\n";

    return 0;
}

int part1() {
    // for this one we got 3 commands, forward, down, up

    // we need to read and parse the file, and based on what command it is, we can add or subtract based on horizontal or vertical; we then multiply the results together
    std::ifstream myFile("input.txt");
    long int horizontal = 0; // For Forward
    long int vertical = 0; // For Up and Down

    std::string command;
    long int num;
    while (myFile >> command >> num) {
        if (command == "forward") {
            horizontal+= num;
        }
        else if (command == "down") {
            vertical+= num;
        }
        else {
            vertical-= num;
        }
    }
    return horizontal * vertical;
}

int part2() {
    // The difference here is on forward, where it increases horizontal position by X && increases depth by aim multiplied by X
    long int vertical = 0;
    long int horizontal = 0;
    long int aim = 0;
    std::string command;
    long int num;
    std::ifstream myFile("input.txt");
    while (myFile >> command >> num) {
        if (command == "forward") {
            horizontal+= num;
            if (aim == 0) continue;
            int temp = aim * num;
            vertical += temp;

        }
        else if (command == "down") {
            aim+= num;
        }
        else {
            aim-= num;
        }
    }
    return horizontal * vertical;
}

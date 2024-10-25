#include <iostream>
#include <vector>
#include <thread>
#include <chrono>
#include <mutex>

class Matrix {
    std::vector<std::vector<int>> data;
    mutable std::mutex mtx;

public:
    Matrix(int rows, int cols) : data(rows, std::vector<int>(cols)) {}

    void setValue(int row, int col, int value) {
        std::lock_guard<std::mutex> guard(mtx);
        data[row][col] = value;
    }

    const std::vector<std::vector<int>>& getData() const { return data; }

    void print() const {
        for (const auto& row : data) {
            for (int val : row) {
                std::cout << val << " ";
            }
            std::cout << std::endl;
        }
    }
};

void multiplyBlock(const std::vector<std::vector<int>>& A, const std::vector<std::vector<int>>& B, Matrix& matrix, int rowStart, int rowEnd, int colStart, int colEnd) {
    int size = A.size();
    for (int i = rowStart; i < rowEnd; ++i) {
        for (int j = colStart; j < colEnd; ++j) {
            int sum = 0;
            for (int k = 0; k < size; ++k) {
                sum += A[i][k] * B[k][j];
            }
            matrix.setValue(i, j, sum);
        }
    }
}

void foxAlgorithm(const std::vector<std::vector<int>>& A, const std::vector<std::vector<int>>& B, Matrix& matrix, int numThreads) {
    int size = A.size();
    int blockSize = size / numThreads;
    std::vector<std::thread> threads;

    for (int i = 0; i < numThreads; ++i) {
        for (int j = 0; j < numThreads; ++j) {
            int rowStart = i * blockSize;
            int rowEnd = (i + 1) * blockSize;
            int colStart = j * blockSize;
            int colEnd = (j + 1) * blockSize;
            threads.emplace_back(multiplyBlock, std::cref(A), std::cref(B), std::ref(matrix), rowStart, rowEnd, colStart, colEnd);
        }
    }

    for (auto& thread : threads) {
        thread.join();
    }
}

std::vector<std::vector<int>> generateMatrix(int size) {
    std::vector<std::vector<int>> matrix(size, std::vector<int>(size));
    for (int i = 0; i < size; ++i) 
        for (int j = 0; j < size; ++j) 
            matrix[i][j] = rand() % 10;

    return matrix;
}

int main() {
    int size = 4; 
    int numThreads = 2; 

    auto A = generateMatrix(size);
    auto B = generateMatrix(size);
    Matrix matrix(size, size);

    auto start = std::chrono::high_resolution_clock::now();
    foxAlgorithm(A, B, matrix, numThreads);
    auto end = std::chrono::high_resolution_clock::now();

    std::chrono::duration<double, std::milli> duration = end - start;
    std::cout << "Time taken: " << duration.count() << " ms" << std::endl;

    matrix.print();

    return 0;
}
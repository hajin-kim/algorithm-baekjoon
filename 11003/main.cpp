#include <iostream>
#include <vector>
#include <deque>
#include <string>
#include <sstream>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int n, l;
    cin >> n >> l;
    cin.ignore();

    string line;
    getline(cin, line);
    istringstream iss(line);

    int a;
    deque<pair<int, int>> deque;
    for (int i = 0; i < n; ++i)
    {
        iss >> a;
        while (!deque.empty() && deque.back().second >= a)
            deque.pop_back();
        deque.push_back({i, a});
        while (deque.front().first <= i - l)
            deque.pop_front();
        cout << deque.front().second << ' ';
    }

    return 0;
}

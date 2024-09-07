
#include <span>
#include <cmath>
#include <queue>
#include <vector>
using namespace std;

class Solution {

    static const int NO_OBSTACLE_FOUND_AT_TARGET_NUMBER_OF_OBSTACLES_FROM_CENTER = -1;

public:
    vector<int> resultsArray(const vector<vector<int>>& queries, int targetNumberOfObstaclesFromCenter) const {
        priority_queue<int> maxHeapDistanceFromCenter;

        vector<int> targetDistanceFromCenterPerQuery(queries.size());
        int index = 0;

        for (const auto& query : queries) {
            maxHeapDistanceFromCenter.push(getDistanceFromCenter(query));

            if (maxHeapDistanceFromCenter.size() > targetNumberOfObstaclesFromCenter) {
                maxHeapDistanceFromCenter.pop();
            }

            if (maxHeapDistanceFromCenter.size() < targetNumberOfObstaclesFromCenter) {
                targetDistanceFromCenterPerQuery[index] = NO_OBSTACLE_FOUND_AT_TARGET_NUMBER_OF_OBSTACLES_FROM_CENTER;
            }
            else {
                targetDistanceFromCenterPerQuery[index] = maxHeapDistanceFromCenter.top();
            }

            ++index;
        }
        return targetDistanceFromCenterPerQuery;
    }

private:
    int getDistanceFromCenter(span<const int> coordinates) const {
        int x = coordinates[0];
        int y = coordinates[1];
        return abs(x) + abs(y);
    }
};

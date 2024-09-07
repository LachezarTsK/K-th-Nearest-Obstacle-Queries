
// const {PriorityQueue} = require('@datastructures-js/priority-queue');
/*
 PriorityQueue is internally included in the solution file on leetcode.
 So, when running the code on leetcode it should stay commented out. 
 It is mentioned here as a comment, just for information about 
 which external library is applied for this data structure.
 */

/**
 * @param {number[][]} queries
 * @param {number} targetNumberOfObstaclesFromCenter
 * @return {number[]}
 */
var resultsArray = function (queries, targetNumberOfObstaclesFromCenter) {
    const NO_OBSTACLE_FOUND_AT_TARGET_NUMBER_OF_OBSTACLES_FROM_CENTER = -1;
    const maxHeapDistanceFromCenter = new MaxPriorityQueue({compare: (first, second) => second - first});

    const targetDistanceFromCenterPerQuery = new Array(queries.length);
    let index = 0;

    for (let query of queries) {
        maxHeapDistanceFromCenter.enqueue(getDistanceFromCenter(query));
        if (maxHeapDistanceFromCenter.size() > targetNumberOfObstaclesFromCenter) {
            maxHeapDistanceFromCenter.dequeue();
        }

        if (maxHeapDistanceFromCenter.size() < targetNumberOfObstaclesFromCenter) {
            targetDistanceFromCenterPerQuery[index] = NO_OBSTACLE_FOUND_AT_TARGET_NUMBER_OF_OBSTACLES_FROM_CENTER;
        } else {
            targetDistanceFromCenterPerQuery[index] = maxHeapDistanceFromCenter.front();
        }

        ++index;
    }
    return targetDistanceFromCenterPerQuery;
};

/**
 * @param {number[]} coordinates
 * @return {number} 
 */
function getDistanceFromCenter(coordinates) {
    const x = coordinates[0];
    const y = coordinates[1];
    return Math.abs(x) + Math.abs(y);
}

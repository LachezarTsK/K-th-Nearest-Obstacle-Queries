
package main

import (
    "container/heap"
    "fmt"
    "math"
)

var NO_OBSTACLE_FOUND_AT_TARGET_NUMBER_OF_OBSTACLES_FROM_CENTER = -1

func resultsArray(queries [][]int, targetNumberOfObstaclesFromCenter int) []int {
    maxHeapDistanceFromCenter := PriorityQueue{
        distanceFromCenter: make([]int, 0),
    }

    targetDistanceFromCenterPerQuery := make([]int, len(queries))
    index := 0

    for _, query := range queries {
        heap.Push(&maxHeapDistanceFromCenter, getDistanceFromCenter(query))

        if maxHeapDistanceFromCenter.Len() > targetNumberOfObstaclesFromCenter {
            heap.Pop(&maxHeapDistanceFromCenter)
        }

        if maxHeapDistanceFromCenter.Len() < targetNumberOfObstaclesFromCenter {
            targetDistanceFromCenterPerQuery[index] = NO_OBSTACLE_FOUND_AT_TARGET_NUMBER_OF_OBSTACLES_FROM_CENTER
        } else {
            targetDistanceFromCenterPerQuery[index] = maxHeapDistanceFromCenter.distanceFromCenter[0]
        }

        index++
    }
    return targetDistanceFromCenterPerQuery
}

func getDistanceFromCenter(coordinates []int) int {
    x := coordinates[0]
    y := coordinates[1]
    return int(math.Abs(float64(x)) + math.Abs(float64(y)))
}

type PriorityQueue struct {
    distanceFromCenter []int
}

func (pq PriorityQueue) Len() int {
    return len(pq.distanceFromCenter)
}

func (pq PriorityQueue) Less(first int, second int) bool {
    return pq.distanceFromCenter[first] > pq.distanceFromCenter[second]
}

func (pq PriorityQueue) Swap(first int, second int) {
    pq.distanceFromCenter[first], pq.distanceFromCenter[second] = pq.distanceFromCenter[second], pq.distanceFromCenter[first]
}

func (pq *PriorityQueue) Push(object any) {
    distance := object.(int)
    (*pq).distanceFromCenter = append((*pq).distanceFromCenter, distance)
}

func (pq *PriorityQueue) Pop() any {
    length := len((*pq).distanceFromCenter)
    distance := ((*pq).distanceFromCenter)[length-1]
    (*pq).distanceFromCenter = ((*pq).distanceFromCenter)[0 : length-1]
    return distance
}

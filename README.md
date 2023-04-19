## **TRAVELLING SALESMAN PROBLEM**

### Problem Statement
Problem is to find the shortest path connecting 585 crime sites. We have the coordinates of cities and are expected to find the best route to visit all crime sites exactly once and return back to starting site.

### APPROACH
We are solving the traveling salesman problem using Christofides Algorithm.  
We are using two Tactical approaches and 2 Strategic optimal approaches to solve the problem and find their shortest path.  

- Tactical approaches used:  
1. Random Swapping
2. Two-opt optimization
3. Three-opt optimization

- Strategic approaches used:
4. Simulated Annealing
5. Ant colony optimization
6. Genetic Algorithm

### ALGORITHM

Christofides Algorithm:  
The traveling salesman problem (TSP), a well-known issue in operations research and computer science, can be roughly solved using the Christofides method, a heuristic technique. The algorithm was created by Nicos Christofides in 1976 and is commonly used in practice because of its effectiveness and simplicity.

  

The algorithm works as follows:

  

1.  Find the minimum spanning tree (MST) of the given graph.
    
2.  Create a minimum-weight perfect matching for the vertices with an odd degree in the MST.
    
3.  Combine the MST and the perfect matching to form a Eulerian tour.
    
4.  Find a Eulerian tour on the Eulerian graph.
    
5.  Convert the Eulerian tour into a Hamilton tour by shortcutting the repeated vertices.
    


### Advantages of Christofides Algorithm:

-   Simplicity: A variety of people can utilize the algorithm because it is reasonably simple to comprehend and apply.
    
-   Low computational complexity: The approach is relatively effective for small to medium-sized instances of the TSP, with a computational complexity of O(n2 log n).
    
-   Performance: Performance is ensured because of the algorithm's worst-case approximation ratio of 3/2, which limits the length of the TSP tour it finds to no more than 1.5 times the length of the ideal TSP tour.
    
-   Widely used: Frequently used as a starting point for more complex algorithms, the Christofides algorithm is a well-liked and commonly used approach for approximating the TSP.
    
-   Good performance in practice: A successful practice performance: Despite the algorithm's worst-case approximation ratio of 3/2

![enter image description here](https://drive.google.com/drive/u/0/my-drive)

Flow Chart:
**![](https://lh4.googleusercontent.com/CKo4QgWH178bqtzxf8mzUpwbqHOZRQr6fcniG9nSumAxOTI9JHihaFt0eppbHX-tBefRUUY-mPeBajiLWJikbAM0hBKJWCq_fLEmrS7s6dWHR8LNyf-sC8AvveGLOXFObS0KaLykUbfSL7KT3wfGtRU)**
**![](https://lh6.googleusercontent.com/jwW7Uoli20M2ZO5ivdmUpEpN7ipQacWJ5TFCOzWbKvt-ZdPqyo8SO71LFkeJN9GLtiAiSJh9SCf07MJRPy4-icFL_Toe73Y9DU8FwIjb1ZXNqpsDXlPJS-UssKlJQxNc1oLEk7QF4XlaL5r8EOCRxfw)**
**![](https://lh5.googleusercontent.com/C8x4rKQ_d9SE5UurrAsR9dkWvZpe9qIfacgkNUypP19otIWATxdJFEtS2aQ5NCgWJ3xHJN-4-Iefo8E-FRF1Fh5TFzg1rR5NnDFf36jIL6sBd8AU79CsSoEh_WQU4nKC6nRPkVeSbZvAUDSnJkS7SrM)**
**![](https://lh6.googleusercontent.com/NN5vopu2H61cS9HKcl7nCdA4C1hZFJEvzEk4IUK5XpceZtL5mNJMEQM0OAOXn0NNVxyLEfWJolO2KdEOTxCu5wJVvnsOWgSSsGkxxkSukGLngZVM-VMQXn24DYCD8pGGJ8l2Azy4_0q3W6URqZB0e24)**
**![](https://lh3.googleusercontent.com/rXyvR8D1fgoujrojFgK8-GKf-ZU_tWiH8JWmCoEg2KxqKKwbUxl4o0Yz0Xh_NL7-ekJPMuGOETBrE-ShzfWc4vNuLaWcfb-9Pinv1yjocGJcCRkICHrZCbtnRVZnoi6Viol8VW3lCTqbyJE7WTJUsB4)**

### CONCLUSION

  

With a polynomial time complexity of O(n3) and a good approximation solution for the issue, the Christofides algorithm for the TSP is concluded to be effective. n is the number of cities. Larger datasets may not have the best solution when using this approach, which performs well for small to medium-sized datasets. But in a fair amount of time, it can still offer a reliable approximation.

  

Additionally, we can enhance the result of the Christofides algorithm by using different optimization techniques such as two opt, three opt, simulated annealing, ant colony optimization, etc. These optimization methods offer an ideal or very close-to-optimal solution by drastically reducing the length of the journey.

  

As a result, we may solve the TSP using the Christofides method as a solid starting point and then use various optimization approaches to reach an optimal or nearly optimal solution. The NP-hardness of the problem may prevent the derived solution from always being the best, but the algorithms employed can still produce efficient and useful solutions for practical needs.

  

Below are the algorithms written from left to right in efficiency or optimization applied for solving TSP

Left most is the most optimized and right most is the lest

  

Ant colony > Simulated annealing > Two opt optimization > Random swapping > Christofides algorithm

### REFERENCES

[https://en.wikipedia.org/wiki/Christofides_algorith](https://en.wikipedia.org/wiki/Christofides_algorithm)m

  

[http://www.cs.cornell.edu/courses/cs681/2007fa/Handouts/christofides.pd](http://www.cs.cornell.edu/courses/cs681/2007fa/Handouts/christofides.pdf)f

  

[https://bochang.me/blog/posts/tsp/](https://bochang.me/blog/posts/tsp/)

  

[http://matejgazda.com/christofides-algorithm-in-python/](http://matejgazda.com/christofides-algorithm-in-python/)

  

[https://en.wikipedia.org/wiki/Christofides_algorithm](https://en.wikipedia.org/wiki/Christofides_algorithm)

  

[https://www.youtube.com/watch?reload=9&v=GiDsjIBOVoA&feature=youtu.be](https://www.youtube.com/watch?reload=9&v=GiDsjIBOVoA&feature=youtu.be)

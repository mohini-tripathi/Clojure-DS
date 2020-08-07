#### Experience Report
### Data Structure and Algorithm with Clojure
#### Dynamic Connectivity



### Introduction
In Dynamic Connectivity, we are given a collection of objects which may vary from a small number of objects to more than thousands of them, in such a scenario it is required to find a path between two objects if they are connected. Dynamic Connectivity is used to determine information about connected components, we will be going through various algorithms and their implementation in Clojure which can perform operations like connecting objects together and finding whether two objects are connected. Our objective will be finding the best suitable algorithm with feasible complexity.
Dynamic Connectivity Problem
The Dynamic Connectivity Problem states - Given N number of objects, prepare a solution such that objects can be connected and a query can be executed which returns whether two objects are connected or not. In the given figure, let the dots and edges represent objects and connections respectively, then we are supposed to find whether there exists a connected path between those two objects.

#### Pre-requisites
- Connected Components - Connected component represents a collection consisting of a maximum number of objects connected. 
- When two objects are assumed to be connected? 
- Let there be three objects named A, B, and C, they are connected when they follow the rule of an equivalence relation. We will be calling the first object as ‘source’ and second object as ‘target’.
- Symmetric - If object A (source) is connected to object B (target) then object B must be connected to object A.
- Reflexive - Object A must always be connected to itself.
- Transitive - If object A is connected to object B, object B is connected to object C, then object A must be connected to object C.

### The Bad Implementation
In Bad Implementation, non-connected objects will be represented as a collection of disjoint sets. To connect two objects, a new list should be returned consisting of the union of source and target, other objects shall be returned as it is. For example -  let 0, 1, 2, 3, 4 represent objects, it can be represented as ( {0} {1} {2} {3} {4} ). On connecting 1 (source) and 2 (target), a new list shall be returned as ( {0} {1 2} {3} {4} ).  On querying connectivity of source and target. We will have to check whether the source and target are in the same set or disjoint.
Let us create an interface consisting of two methods -
- connect - It is used to connect source and target.
- connected? - It returns true if the source and target are connected.
- Define a function connected-component which is supposed to return a list of disjoint sets of N number of arguments. The function would take N number of arguments and map each argument to a set disjointly, returning a list of them that is stored in the components. Constructor ->DCImpl is called to accept components to return to the record.
- Define the connect method, it would take three arguments i.e components (the one we defined earlier), source, and target. We are supposed to connect the source and target, to achieve this, we can loop over the components to return a new set consisting of source and target. Furthermore, existing components must be filtered to remove the source and target in order to conjoin the new set. This would return   ( {object1} {object2} {source target} . . .) for ( {object1} {object2} {source} {target} . . .)



We have successfully implemented connect, now let us proceed to connected?
To query about connectivity, we need to check whether the source, as well as target, exists in the same set. It can be accomplished by looping over the components then checking whether the element contains source, as well as the target in the same set by using Clojure’s contains method? 

We are done with both the methods, let us examine their complexity.

#### Complexity
- Complexity for connected? Is the order of O(N) in the worst case since it will iterate over the individual elements of the components.
- Complexity for connect would be O(2N)

Clearly, this complexity for a large set of input is very bad, the application of  Dynamic Connectivity involves working with networks, pixels where we have to deal with an enormous number of objects.
### Quick Find

Instead of using sets, we would be using vectors. 
- Objects are be represented by indices of the vector.
- connected- components are to be represented by values fo the vector. 
Representing objects by indices makes it a lot easier to perform operations on them. Initially, values and indices of the vector are initiated to be the same, to connect source and target, the corresponding value of that index would be updated to be the same. For example, let the vector be [0 1 2 3 4] if the object corresponding to index 1 and index 2 is supposed to be connected, then the vector is updated as [0 2 2 3 4] or [0 1 1 3 4]. 
Now, it can easily be queried whether two indices have the same value to check connectivity. This would reduce complexity for connected? to O(1), great!. Let’s proceed to implementation.
Let’s define connected-components named as connected-ids here. We are supposed to return vector for N arguments, this can be achieved by mapping each item with provided arguments individually, then converting the list into a vector. The vector obtained can be stored in a variable and the constructor ->QuickFind must be called to return ids into the record.
To implement connect -
- It will take three arguments, i.e ids, source, and target.
- Store vector’s values corresponding to source and target in sID and tID
- Loop over the ids (components) to find the value which is equal to sID and replace it with tID using Clojure’s assoc method. This would return a vector with the same value of source and target. Finally, return the new vector obtained.
- To implement connected? we  just need to compare id[source], id[target] to be equal.
#### Complexity
- connect is O(N) due to looping over the component.
- connected? Is O(1) due to just comparing two items, that’s why it is Quick Find.


### Quick Union
The entire vector structure would depict a tree structure consisting of parents, roots, and indices (objects).
Unlike, Quick Find, values corresponding to indices are an immediate parent of the objects. In a vector [0 1 2 3 4] if 1 is supposed to be connected to 2, then immediate parent, as well as the root of 1, is 2. If 3 is now connected to them (1->2->3) then immediate root(1), root(2), root(3) is 3, whereas, immediate parent of 1 is 2.
connected?  would work by comparing root of the source and root of the target to be equal as the root of all the connected objects would be the same.
Let’s implement the root method now, it would accept two arguments, components, and the item whose root is required to be evaluated. The root can be obtained by obtaining ...id(id(id(item))), in other words, calculating immediate parents over and over again of connected objects would eventually give us the root. Loop over the components with a terminating condition where iteration (starting from 0) is equal to the current item while looping.
In connect, the root of source and target must be stored in a variable that is further used to replace the root of source or target to another one such that both are the same.
Complexity
This implementation would reduce complexity O(N) to O(N’) where N’ is the height of the tree for the connect method. Complexity for connected? would be the same.


### Weighted Quick Union
Weighted Quick Union would introduce another parameter i.e size. It is the size of connected objects. For non-connected items [0 1 2 3 4],  size of each item can be initialized as 0 or 1, [1 1 1 1 1]. 1 would be connected to 2. Size of 2 will increase to 2. Furthermore, size of 2 (root) would increase to 3 on connecting 3 to it and so on.
Why do we require size? As we noticed that complexity in Quick Union id of the order or the height (which is linear), this can be further enhanced by not letting the tree grow quickly. For that, we need to compare the size of the source to size of the target. It will generate three conditions i.e - 
- If Size(Source) = Size(target): In this case, it doesn’t matter which tree should be appended to another, so we can go any of the ways.
- If Size(Source) > Size(target): In this case, adding source tree to target tree would increase the overall height of the tree, thus, we should go with appending target tree to source tree.
- If Size(Source) < Size(target): this time, the source tree must be added to the target tree in order to maintain the height of the new tree.
 These conditions are implemented in Clojure by using Clojure’s cond method to check conditions. Great! We accomplished to reduce the height of the tree.
This can furthermore be optimized by using path compression.

### Path Compression
As we can observe root, it uses looping to figure out the root of an item. We are supposed to compress this path. Initially, the tree of the connected component would be like 1->2->3. To get to the root, we need to go through 2 other elements in this example. What if each connected item returns root itself? This would definitely reduce the time taken while finding the root. Thus, we used to reduce that time in the following change, now each item won’t be iterated and this will optimize the complexity furthermore.

# sperner-family

Implements Sperner family as a data structure.

I'm pretty sure that checking a subset relationship between two sets will always take $O(n \log n)$ time, since it must check that every element of the subset is in the superset, assuming checking set membership takes $O(\log n)$. Same is true for checking set equality.

So I figured the best implementation was the naive "set (family) implemented as a list", since no matter what set is added to the family, it must be checked against all existing sets for a subset relationship, which incidentally checks for equality.

I would use a hashset instead of a list to represent the Sperner family if there exists a hashing algorithm on sets that indicates subset as well as equality, but I don't know of one There's also the question of using a red-black tree to represent the family, if somehow subsets could be preserved. The issue is that in the worst case there could be $n$ deletions if, say, the empty set is inserted into the family.

It would be nice to implement the interface `Set<Set<T>>`, but it requires defining removing a set from a Sperner family. But I don't know how to answer this question: Should removing a set from a Sperner family restore the previously-added supersets that were shadowed by this set? That is, should the sequence of statements `family.add(set); family.remove(set);` cancel each other out? If the answer is yes, then I figure that the data structure must use trees to keep track of shadowed supersets. For my original purposes, all I needed to do was add to the Sperner family, so I never addressed this issue.

It would be nice as well to define intersection and union of two Sperner families, but I don't know how to do that efficiently.

For union, although there are no subset relationships in the family directly, there may be subset relationships between the sets in both families, so the best I can come up with is looping through both families and performing `add()` on their sets into a resultant family. That would have an $O(n^3 \log n)$ running time for sets with $n$ elements and $n$ sets in each family.

For intersection, by definition, the intersection of two Sperner families do not have any subset relationships, so at least that step does not need to be checked. But by representing the families as lists, you still need to pairwise check every element of both to see if they are equal, so it is still $O(n^3 \log n)$.


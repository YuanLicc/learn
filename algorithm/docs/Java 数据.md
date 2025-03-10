##### 集合类
- List => 
  - add
  - remove
  - set
  - get

- Queue => 队列
  - poll remove head and return
  - peek return head
  - add 插入元素，如果容量满了，抛出异常
  - offer 插入元素，如果容量满了，返回 false

- Deque extends Queue => 双向队列
  - addFirst、addLast、removeFirst、removeLast、offerFirst、offerLast
  - pollFirst、pollLast
  - getFirst、getLast
  - peekFirst、peekLast
  - add = addLast
  - offer = offerLast
  - remove = removeFirst
  - poll = pollFirst
  - peek = peekFirst
  - push = addFirst
  - pop = removeFirst

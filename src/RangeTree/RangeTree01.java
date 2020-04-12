package RangeTree;

import interfaces.IAbstractRangeTree;
import interfaces.ICoordinates;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class RangeTree01<D extends ICoordinates> implements IAbstractRangeTree<D> {
    private Node root;
    private Node lastLeaf;


    @Override
    public void build(List<D> list) {
        if (list != null && list.size() != 0) {
            root = buildTree(list, null, true);
        }
    }

    private Node buildTree(List<D> list, Node parent, boolean isDimX) {
        Node newElement;
        if (list.size() >= 2) {
            newElement = navigationNode(list, isDimX);
        } else if (list.size() == 1) {
            newElement = buildLeaf(list, lastLeaf);
            lastLeaf = newElement;
        } else {
            return null;
        }

        newElement.parent = parent;

        if (newElement.data == null && isDimX) {
            Node tmp = lastLeaf;
            lastLeaf = null;
            newElement.secondaryTree = buildTree(list, null, false);
            if (newElement.secondaryTree != null) {
                newElement.secondaryTree.parent = newElement;
            }
            lastLeaf = tmp;
        }
        return newElement;
    }

    private Node navigationNode(List<D> list, boolean isDimX) {
        sortList(list, isDimX);

        List<D> firstHalf = new ArrayList<>();
        List<D> secondHalf = new ArrayList<>();
        Node newElement = new Node();

        newElement.median = divideList(list, firstHalf, secondHalf, isDimX);

        newElement.left = buildTree(firstHalf, newElement, isDimX);
        newElement.right = buildTree(secondHalf, newElement, isDimX);
        return newElement;
    }

    private double divideList(List<D> list, List<D> firstHalf, List<D> secondHalf, boolean isDimX) {
        double median = median(list, isDimX);
        if (isDimX) {
            for (D d : list) {
                if (d.getX() <= median) {
                    firstHalf.add(d);
                } else {
                    secondHalf.add(d);
                }
            }
        } else {
            for (D d : list) {
                if (d.getY() <= median) {
                    firstHalf.add(d);
                } else {
                    secondHalf.add(d);
                }
            }
        }
        return median;
    }

    private double median(List<D> list, boolean isDimX) {
        sortList(list, isDimX);
        double median;
        // get count of scores
        int totalElements = list.size();
        // check if total number of scores is even
        if (totalElements % 2 == 0) {
            int sumOfMiddleElements;
            if (isDimX) {
                sumOfMiddleElements = list.get((totalElements / 2)).getX() + list.get((totalElements / 2 - 1)).getX();
            } else {
                sumOfMiddleElements = list.get((totalElements / 2)).getY() + list.get((totalElements / 2 - 1)).getY();
            }
            // calculate average of middle elements
            median = ((double) sumOfMiddleElements) / 2;
        } else {
            // get the middle element
            if (isDimX) {
                median = (double) list.get((list.size() / 2)).getX();
            } else {
                median = (double) list.get((list.size() / 2)).getY();
            }
        }
        return median;
    }

    private Node buildLeaf(List<D> list, Node lastNode) {
        Node leafList = new Node(list.get(0));
        if (lastNode != null) {
            lastNode.next = leafList;
            leafList.previous = lastNode;
        }
        return leafList;
    }

    private void sortList(List<D> list, boolean isDimX) {
        if (isDimX) {
            list.sort(((o1, o2) -> {
                if(o1.getX() == o2.getX()) {
                    return 0;
                }
                return o1.getX() - o2.getX();
            }));
        } else {
            list.sort((o1, o2) -> {
                if(o1.getY() == o2.getY()) {
                    return 0;
                }
                return o1.getY() - o2.getY();
            });
        }
    }

    @Override
    public D simplyFind(D coordinate) {
        Node temp = root;

        while (temp != null) {
            if (temp.left != null && temp.left.data != null && temp.left.data.compareTo(coordinate) == 0) {
                return temp.left.data;
            }

            if (temp.right != null && temp.right.data != null && temp.right.data.compareTo(coordinate) == 0) {
                return temp.right.data;
            }

            if (validateMoveLeft(temp, coordinate)) {
                temp = temp.left;
                continue;
            }

            if (validateMoveRight(temp, coordinate)) {
                temp = temp.right;
                continue;
            }
            break;
        }
        return null;
    }

    private boolean validateMoveLeft(Node temp, D coordinate) {
        return temp.left != null && temp.left.data == null && temp.median >= coordinate.getX();
    }

    private boolean validateMoveRight(Node temp, D coordinate) {
        return temp.right != null && temp.right.data == null && temp.median <= coordinate.getX();
    }

    private Node simplyFindMin(D coordinate) {
        Node temp = root;

        while (temp != null) {
            if (validateMoveLeft(temp, coordinate)) {
                temp = temp.left;
                continue;
            }

            if (validateMoveRight(temp, coordinate)) {
                temp = temp.right;
                continue;
            }

            if (temp.left != null && temp.left.data != null && temp.left.data.getX() >= coordinate.getX()) {
                return temp.left;
            }

            if (temp.right != null && temp.right.data != null && temp.right.data.getX() >= coordinate.getX()) {
                return temp.right;
            }
            break;
        }
        return null;
    }

    @Override
    public List<D> intervalFind(D souMin, D souMax) {
        if (root == null) {
            return null;
        }
        if(souMin.getX() > souMax.getX()) {
            D temp = souMax;
            souMax = souMin;
            souMin = temp;
        }
        List<D> list = new ArrayList<>();

        Node tmp = simplyFindMin(souMin);

        while (tmp != null) {
            if (tmp.data.getX() >= souMin.getX() && tmp.data.getX() <= souMax.getX()) {
                if (tmp.data.getY() >= souMin.getY() && tmp.data.getY() <= souMax.getY()) {
                    list.add(tmp.data);
                }
            } else {
                break;
            }
            tmp = tmp.next;
        }
        return list;
    }

    private class Node {
        D data;
        Node parent;
        Node left;
        Node right;
        Node next;
        Node previous;
        Node secondaryTree;

        Double median;

        Node() {
        }

        Node(D data) {
            this.data = data;
        }
    }
}

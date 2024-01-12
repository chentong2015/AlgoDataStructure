package data_structure.tree.red_back_tree;

// 红黑树使用场景:
// TreeMap, TreeSet
// Hashmap, ConcurrentHashmap > JDK1.8
// Linux内核中完全公平调度
// 高精度计时器
// ext3文件系统
public class BaseRedBackTree {

    // 红黑树的生成:
    // 1. 忽略红色结点，只需要"黑色结点"平衡
    // 2. 不能出现两个连续的红色结点
    // 3. 平衡措施: 变色&自旋(左右旋)

    // 红黑树的插入: log(n)
    // 1. 插入到新结点必须是红色
    // 2. 查看是否符合定义的5条定义
    // 3. 是否需要变色或者是旋转平衡
    // 4. 如果插入相同的值，看具体的实现，插入左边右边都可以

    // TODO: 插入结点的所有可能, 先调整"三层子树"成红黑树
    // 父结点是黑色，则不需要调整
    // 父结点是红色
    //   叔叔结点为空或者黑色，则(左右)旋转+变色  ==> 有可能先左旋，然后右旋
    //   叔叔结点为红色，则父结点+叔叔结点变成黑色，祖父结点变成红色
    static <K, V> TreeNode<K, V> balanceInsertion(TreeNode<K, V> root, TreeNode<K, V> x) {
        x.red = true; // 新插入的结点初始是红色结点
        // xp父结点，xpp祖父结点，xppl祖父结点的左孩子结点，xppr祖父结点的右孩子结点
        for (TreeNode<K, V> xp, xpp, xppl, xppr; ; ) {
            if ((xp = x.parent) == null) {
                // 如果父结点为null，则说明插入的是root结点，为黑色结点
                x.red = false;
                return x;
            } else if (!xp.red || (xpp = xp.parent) == null) {
                // 如果父结点不是红色或者祖父结点为null，则直接返回，不需要调整
                return root;
            }
            if (xp == (xppl = xpp.left)) { // xp是祖父结点的左边结点
                if ((xppr = xpp.right) != null && xppr.red) {
                    // 父结点是红色，并且叔叔结点也是红色
                    // 则父结点+叔叔结点变成黑色，祖父结点变成红色 !!
                    xppr.red = false;
                    xp.red = false;
                    xpp.red = true;
                    // 将子树的祖父结点作为新的结点x，进行后续递归调整(往上)
                    x = xpp;
                } else {
                    // 没有叔叔结点，或者叔叔结点是黑色的
                    if (x == xp.right) {
                        // 新的x结点插入在父结点的右边，则需要先"左旋"，后面再"右选"
                        // root = rotateLeft(root, x = xp);
                        xpp = (xp = x.parent) == null ? null : xp.parent;
                    }
                    // 如果新的x结点插入在父结点的左边，则后面只是需要"右旋"
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            // 上面先变色，再右旋
                            // root = rotateRight(root, xpp);
                        }
                    }
                }
            } else { // xp是祖父结点的右边结点
                if (xppl != null && xppl.red) {
                    xppl.red = false;
                    xp.red = false;
                    xpp.red = true;
                    x = xpp;
                } else {
                    if (x == xp.left) {
                        // 反向操作，先右旋转，后面再左旋转
                        // root = rotateRight(root, x = xp);
                        xpp = (xp = x.parent) == null ? null : xp.parent;
                    }
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            // 这里的左旋传入的是xpp祖父结点，把xpp进行旋转
                            // root = rotateLeft(root, xpp);
                        }
                    }
                }
            }
        }
    }

    // 注意大前提: if (xp == (xppl = xpp.left)) {}
    // p基本结点，pl为p的左结点m，xp父结点，pp祖父结点
    static <K, V> TreeNode<K, V> rotateLeft(TreeNode<K, V> root, TreeNode<K, V> xp) {
        TreeNode<K, V> p, pp, pl;
        if (xp != null && (p = xp.right) != null) {  // 以p为圆心进行旋转
            if ((pl = xp.right = p.left) != null)    // p原本有左孩子结点，使用pl变量表示
                pl.parent = xp;                      // 将pl作为xp的右结点，并建立父子关系
            if ((pp = p.parent = xp.parent) == null) // 将原来父结点xp的父结点赋值给p的父结点，使用pp结点表示祖父结点
                (root = p).red = false;              // pp为空，则直接将旋转的圆心p作为root结点，设置成黑色
            else if (pp.left == xp)                  // 原来xp父结点在祖父结点的左边，则将p设置成祖父新的左结点
                pp.left = p;
            else
                pp.right = p;  // 反之将p设置成祖父新的右结点
            p.left = xp;       // 旋转之后p成为xp的父结点
            xp.parent = p;     // xp的父结点为p
        }
        return root;
    }

    // 右旋转和左旋转相互对称
    static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root, TreeNode<K, V> p) {
        TreeNode<K, V> l, pp, lr;
        if (p != null && (l = p.left) != null) {
            if ((lr = p.left = l.right) != null)
                lr.parent = p;
            if ((pp = l.parent = p.parent) == null)
                (root = l).red = false;
            else if (pp.right == p)
                pp.right = l;
            else
                pp.left = l;
            l.right = p;
            p.parent = l;
        }
        return root;
    }

    // 红黑树的查询: log(n)

    // 红黑树的删除: 代码和场景复杂
}
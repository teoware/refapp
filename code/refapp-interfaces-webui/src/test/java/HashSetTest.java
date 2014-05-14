/*
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

    private static final Logger LOG = LoggerFactory.getLogger(HashSetTest.class);
    private static final String A_PREFIX = "a";
    private static final String B_PREFIX = "b";
    private static final int SIZE = 1000;

    private Set<Item> a;
    private Set<Item> b;

    @Test
    public void test() {
        DateTime overall = DateTime.now();

        init:
        {
            DateTime init = DateTime.now();
            init();
            log("Init", init);
        }

        sort: {
            DateTime sort = DateTime.now();
            sort();
            log("Sort", sort);
        }

        log("Overall", overall);
        log();
    }

    private void init() {
        a = new HashSet<Item>();
        b = new HashSet<Item>();
        int e = 0;
        int u = 0;
        int o = 0;
        for (int i = 0; i < SIZE; i++) {
            a.add(aItem(i));

            double random = Math.random();
            if (random < 0.2) {
                e++;
                b.add(aItem(i));
            } else if (random > 0.8) {
                u++;
                b.add(updateItem(i));
            } else {
                o++;
                b.add(bItem(i));
            }
        }
        LOG.info("e={}, u={}, o={}", e, u, o);
    }

    private void sort() {
        Set<Item> n = new HashSet<Item>(a);
        Set<Item> c = new HashSet<Item>(a);
        Set<Item> e = new HashSet<Item>(a);
        n.removeAll(b);
        e.retainAll(b);
        Iterable<Item> filtered = Iterables.filter(a, new Predicate<Item>() {
            @Override
            public boolean apply(Item item) {
                return true;
            }
        });
        Set<Item> c = new HashSet<Item>(filtered);
        log(n, c, e);
        //log(e);
    }

    private Item aItem(int index) {
        return new Item(key(A_PREFIX, index), value(A_PREFIX, index));
    }

    private Item bItem(int index) {
        return new Item(key(B_PREFIX, index), value(B_PREFIX, index));
    }

    private Item updateItem(int index) {
        return new Item(key(A_PREFIX, index), value(B_PREFIX, index));
    }

    private String key(String prefix, int index) {
        return prefix + index;
    }

    private String value(String prefix, int index) {
        return key(prefix, index) + "_value";
    }

    private void log(String event, DateTime start) {
        long millis = new Duration(start, DateTime.now()).getMillis();
        LOG.info("-- {} took {}ms", event, millis);
    }

    private void log() {
        LOG.info("a.size={} b.size={}", a.size(), b.size());
    }

    private void log(Set<Item> n, Set<Item> c, Set<Item> e) {
        LOG.info("n.size={} c.size={} e.size={}", n.size(), c.size(), e.size());
    }

    private void log(Set<Item> s) {
        for (Item i : s) {
            LOG.info("### {}", i);
        }
    }

    public class Item {

        private String key;
        private String value;

        public Item() {
        }

        public Item(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Item item = (Item) o;

            if (key != null ? !key.equals(item.key) : item.key != null) return false;
            if (value != null ? !value.equals(item.value) : item.value != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return String.format("Item{key='%s', value='%s'}", key, value);
        }
    }
}
*/

package schedule.repo;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Andris Jansons
 */
public interface Repo {
    public ArrayList read(Connection conn);
    void write(Connection conn, ArrayList list);
}

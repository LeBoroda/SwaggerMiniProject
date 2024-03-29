package logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ILoggable {

    default Logger log() {
        return LoggerFactory.getLogger(this.getClass());
    }

}

package test.de.jro.moduls.utils.mocks;

import java.util.concurrent.Callable;

public class CallableDummy implements Callable<Boolean> {

    public CallableDummy(Integer param) {

    }

    @Override
    public Boolean call() throws Exception {
        try {
            this.doSth();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {

        }
    }

    private void doSth()
            throws Exception {
        return;
    }
}

package cn.net.sybt.springboot.utilsTest;

import cn.net.sybt.springboot.utils.LicenseCodeGenerator;
import org.junit.jupiter.api.Test;

class LicenseCodeGeneratorTest {

    @Test
    void nextCode() {
        assert LicenseCodeGenerator.nextCode().length() == 8;
    }
}
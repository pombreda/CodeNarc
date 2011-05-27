/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codenarc.rule.design

import org.codenarc.rule.AbstractRuleTestCase
import org.codenarc.rule.Rule

/**
 * Tests for PublicInstanceFieldRule
 *
 * @author Victor Savkin
 * @version $Revision: 329 $ - $Date: 2010-04-29 04:20:25 +0200 (Thu, 29 Apr 2010) $
 */
class PublicInstanceFieldRuleTest extends AbstractRuleTestCase {

    void testRuleProperties() {
        assert rule.priority == 2
        assert rule.name == 'PublicInstanceField'
    }

    void testShouldAddNoViolationsForPrivateField() {
        final SOURCE = '''
        	class Person {
                private String name
            }
        '''
        assertNoViolations SOURCE
    }

    void testShouldAddViolationForPublicField() {
        final SOURCE = '''
            class Person {
                public String name
            }
        '''
        assertSingleViolation SOURCE, 3, null, "Using public fields is considered bad design. Create property 'name' instead."
    }

    void testShouldAddViolationForPublicFieldWithInitializer() {
        final SOURCE = '''
            class Person {
                public String name = 'John'
            }
        '''
        assertSingleViolation SOURCE, 3, null, "Using public fields is considered bad design. Create property 'name' instead."
    }

    void testShouldAddNoViolationsForPublicStaticField() {
        final SOURCE = '''
        	class Person {
                public static String name
            }
        '''
        assertNoViolations SOURCE
    }

    protected Rule createRule() {
        new PublicInstanceFieldRule()
    }
}
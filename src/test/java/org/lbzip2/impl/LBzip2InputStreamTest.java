/*-
 * Copyright (c) 2014 Mikolaj Izdebski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lbzip2.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.lbzip2.StreamFormatException;

/**
 * @author Mikolaj Izdebski
 */
public class LBzip2InputStreamTest
    extends AbstractDecompressorTest
{
    @Override
    protected void oneFile( InputStream fis, String md5 )
        throws Exception
    {
        try
        {
            LBzip2InputStream zis = new LBzip2InputStream( fis );
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            int r;
            while ( ( r = zis.read( buf ) ) != -1 )
                out.write( buf, 0, r );
            zis.close();
            if ( md5 == null )
                fail();
            assertEquals( md5, md5( out.toByteArray() ) );
        }
        catch ( StreamFormatException e )
        {
            if ( md5 != null )
                throw e;
        }
    }
}

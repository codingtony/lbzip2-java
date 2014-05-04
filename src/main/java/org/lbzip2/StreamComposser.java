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
package org.lbzip2;

import java.io.IOException;

/**
 * @author Mikolaj Izdebski
 */
public class StreamComposser
    implements DataSource
{
    public StreamComposser()
    {
        // TODO Auto-generated constructor stub
    }

    public StreamComposser( int maxBlockSize )
    {
        // TODO Auto-generated constructor stub
    }

    public void addBlock( CompressedBlock block )
    {
        // TODO Auto-generated method stub
    }

    public void finish()
    {
        // TODO Auto-generated method stub
    }

    public boolean isEmpty()
        throws IOException
    {
        // TODO Auto-generated method stub
        return false;
    }

    public int read()
        throws IOException
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public int read( byte[] buf )
        throws IOException
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public int read( byte[] buf, int off, int len )
        throws IOException
    {
        // TODO Auto-generated method stub
        return 0;
    }
}

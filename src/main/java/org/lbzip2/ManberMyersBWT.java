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

import static org.lbzip2.Constants.CHARACTER_BIAS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A BWT implementation based on the original Manber-Myers algorithm.
 * <p>
 * Implementation is directly based on paper <em>Suffix arrays: a new method for on-line string searches</em>.
 * 
 * @author Mikolaj Izdebski
 */
class ManberMyersBWT
    implements BWT
{
    private final Logger logger = LoggerFactory.getLogger( ManberMyersBWT.class );

    public int transform( byte[] D, int[] P, int n )
    {
        logger.debug( "Manber-Myers BWT, block size {}", n );

        int[] R, C;
        boolean[] A, B;
        int i, j, d, e, h;

        A = new boolean[n];
        B = new boolean[n + 1];
        C = new int[Math.max( n, 256 )];
        R = new int[n];

        logger.trace( "  Bucket sorting..." );

        for ( i = 0; i < 256; i++ )
            C[i] = 0;
        for ( i = 0; i < n; i++ )
            C[D[i] + CHARACTER_BIAS]++;
        for ( i = 1; i < 256; i++ )
            C[i] += C[i - 1];
        for ( i = 0; i < n; i++ )
            P[--C[D[i] + CHARACTER_BIAS]] = i;

        for ( i = 0; i < n; i++ )
            B[i] = false;
        for ( i = 0; i < 256; i++ )
            B[C[i]] = true;
        B[n] = true;

        for ( h = 1; h < n; h *= 2 )
        {
            logger.trace( "  Suffix sorting at depth {}...", h );

            for ( i = 0; i < n; i = j )
            {
                C[i] = i;
                for ( j = i; j == i || !B[j]; j++ )
                {
                    R[P[j]] = i;
                    A[j] = false;
                }
            }

            for ( i = 0; i < n; i = j )
            {
                for ( j = i; j == i || !B[j]; j++ )
                {
                    d = ( P[j] - h + n ) % n;
                    e = R[d];
                    R[d] = C[e]++;
                    A[R[d]] = true;
                }

                for ( j = i; j == i || !B[j]; j++ )
                {
                    d = ( P[j] - h + n ) % n;
                    e = R[d];
                    if ( A[e] )
                    {
                        e++;
                        while ( !B[e] && A[e] )
                        {
                            A[e] = false;
                            e++;
                        }
                    }
                }
            }

            for ( i = 0; i < n; i++ )
            {
                P[R[i]] = i;
                B[i] |= A[i];
            }
        }

        logger.trace( "  Constructing BWT..." );

        j = R[0];
        P[j] = n;
        for ( i = 0; i < n; i++ )
            P[i] = D[P[i] - 1] + CHARACTER_BIAS;

        return j;
    }
}

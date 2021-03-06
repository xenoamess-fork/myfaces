/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.myfaces.view.facelets.pool.impl;

import java.lang.ref.WeakReference;
import javax.faces.component.UIViewRoot;
import org.apache.myfaces.view.facelets.pool.RestoreViewFromPoolResult;
import org.apache.myfaces.view.facelets.pool.ViewEntry;

/**
 *
 * @author Leonardo Uribe
 */
public class WeakViewEntry extends ViewEntry
{
    private WeakReference<UIViewRoot> viewRootRef;
    private UIViewRoot viewRoot;
    private RestoreViewFromPoolResult result;

    public WeakViewEntry(UIViewRoot viewRoot)
    {
        this.viewRootRef = new WeakReference<>(viewRoot);
    }
    
    @Override
    public boolean activate()
    {
        viewRoot = viewRootRef.get();
        viewRootRef = null;
        return viewRoot != null;
    }

    @Override
    public UIViewRoot getViewRoot()
    {
        if (viewRootRef != null)
        {
            return viewRootRef.get();
        }
        return viewRoot;
    }

    public void setViewRoot(UIViewRoot viewRoot)
    {
        this.viewRootRef = new WeakReference<>(viewRoot);
        this.viewRoot = null;
    }

    @Override
    public RestoreViewFromPoolResult getResult()
    {
        return result;
    }

    @Override
    public void setResult(RestoreViewFromPoolResult result)
    {
        this.result = result;
    }
}
